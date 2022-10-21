package com.pda.itoken.service.upload.controller;

import com.pda.itoken.service.upload.fastdfs.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PDA
 * @Date 2022/10/21 21:25
 * @Description 文件控制器
 * @since version-1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UploadController {
	@Value("${fastdfs.base.url}")
	private String FASTDFS_BASE_URL;

	@Autowired
	private StorageService storageService;

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:26
	 * @Description 文件上传
	 * @Param [dropFile, editorFiles]
	 * @return Map<String,Object>
	 * @since version-1.0
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public Map<String, Object> upload(MultipartFile dropFile, MultipartFile[] editorFiles) {
		Map<String, Object> result = new HashMap<>();

		// Dropzone 上传
		if (dropFile != null) {
			result.put("fileName", writeFile(dropFile));
		}

		// wangEditor 上传
		if (editorFiles != null && editorFiles.length > 0) {
			List<String> fileNames = new ArrayList<>();

			for (MultipartFile editorFile : editorFiles) {
				fileNames.add(writeFile(editorFile));
			}

			result.put("errno", 0);
			result.put("data", fileNames);
		}

		return result;
	}

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:26
	 * @Description 将图片写入指定目录
	 * @Param [multipartFile]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	private String writeFile(MultipartFile multipartFile) {
		// 获取文件后缀
		String oName = multipartFile.getOriginalFilename();
		String extName = oName.substring(oName.lastIndexOf(".") + 1);

		// 文件存放路径
		String url = null;
		try {
			String uploadUrl = storageService.upload(multipartFile.getBytes(), extName);
			url = FASTDFS_BASE_URL + uploadUrl;
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回文件完整路径
		return url;
	}
}
