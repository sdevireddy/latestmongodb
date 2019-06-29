package com.agent.autojob;

import java.io.FileWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
//@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	@Autowired
	private ConfigurableApplicationContext context;

	@Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd\").parse(\"${job.startdate}\")}")
	// @DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date startDate;
	// @Value("${job.enddate}")
	// @DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	@Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd\").parse(\"${job.enddate}\")}")
	private Date endDate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("start values is " + dateFormat.format(startDate));
		String filePath = "C:\\Users\\sdevireddy\\mongodbws\\gs-accessing-data-mongodb-complete\\config.properties";
		Properties prop = new Properties();
		try (Writer inputStream = new FileWriter(filePath)) {
			prop.setProperty("job.startdate", dateFormat.format(getResultEndDate(1)));// .replace("\\:", ":"));
			prop.setProperty("job.enddate", dateFormat.format(getResultEndDate(2)));// ).replace("\\:", ":"));
			prop.store(inputStream, "job  information");
		}
		System.exit(SpringApplication.exit(context));
	}

	private Date getResultEndDate(int days) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(dateFormat.format(startDate)));
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}

}
