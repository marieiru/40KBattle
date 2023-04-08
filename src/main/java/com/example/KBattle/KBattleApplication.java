package com.example.KBattle;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KBattleApplication extends SpringBootServletInitializer {

	public KBattleApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(KBattleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KBattleApplication.class);
	}

	@Bean //@Beanアノテーションを付けたメソッドを定義しておくことでSpring Frameworkがオブジェクトを登録して後で使える
	public AppConfig appConfig() {

		// 起動時のディレクトリをAppConfigのimageDirフィールドに保持しておく
		File imageDir = new File("images");
		imageDir = imageDir.getAbsoluteFile();

		// imagesフォルダがなかったら作成する
		if (!imageDir.exists()) {
			imageDir.mkdir();
		} else {
			File file = new File(imageDir + "/picture.jpg");

			//deleteメソッドを使用してファイルを削除する
			file.delete();
		}
		AppConfig appConfig = new AppConfig();
		appConfig.setImageDir(imageDir);
		return appConfig;
	}

}
