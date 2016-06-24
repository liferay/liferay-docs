package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class CheckImageUtil {
	
	public static List<String> checkImgSrc(
			String content, String article, int line, int dirsUp, String imageFolder) {

		List<String> warnings = new ArrayList<String>();

		int pos = -1;
		while ((pos=content.indexOf("![")) != -1) {
			int x = content.indexOf("](", pos);
			int y = content.indexOf(")", x);

			if (y != -1) {
				String imgSrc = content.substring(x + 2, y);

				StringBuffer imagePathStart = new StringBuffer(dirsUp);
				for (int i = 0; i < dirsUp; i++) {
					imagePathStart.append("../");
				}

				String targetImagePath = imagePathStart.toString() +
					imageFolder + "/";
				if (!imgSrc.startsWith(targetImagePath)) {
					StringBuffer sb = new StringBuffer(6);
					sb.append("Invalid image path: ");
					sb.append(imgSrc);
					sb.append(" in ");
					sb.append(article);
					sb.append(":");
					sb.append(line);
					warnings.add(sb.toString());
				}
				else {
					imgSrc = imgSrc.replace(targetImagePath, "./" + imageFolder + "/");

					if (!imgSrc.contains("CDATA") &&
						!(new File(imgSrc).exists())) {

						StringBuffer sb = new StringBuffer("\t");
						sb.append(imgSrc);
						sb.append(" in ");
						sb.append(article);
						sb.append(":");
						sb.append(line);
						warnings.add(sb.toString());
					}
				}

				content = content.substring(y + 1);
			}
			else if (x != -1){
				content = content.substring(x + 2);
			}
			else {
				content = content.substring(pos + 2);
			}
		}

		return warnings;
	}
}
