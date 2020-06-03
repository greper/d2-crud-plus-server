package com.veryreader.d2p.api.modules.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.upload.StorageManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Slf4j
public class CustomBinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;


		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
		if (!multipartResolver.isMultipart(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}


//        if ( isAjaxUpload ) {
//            upload.setHeaderEncoding( "UTF-8" );
//        }

		try {
			MultipartFile multipartFile = null;
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> names = multiRequest.getFileNames();
			while (names.hasNext()) {
				multipartFile = multiRequest.getFile(names.next().toString());
			}

			if (multipartFile == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;
			File targetFile = new File(physicalPath);
			if(!targetFile.getParentFile().exists()){
				targetFile.getParentFile().mkdirs();
			}
			multipartFile.transferTo(targetFile);
			State storageState = new BaseState(true);
			storageState.putInfo( "size", targetFile.length() );
			storageState.putInfo( "title", targetFile.getName() );
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return new BaseState(false, AppInfo.IO_ERROR);
		}

	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
