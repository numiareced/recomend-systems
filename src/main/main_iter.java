package main;

import java.io.IOException;

import algorithms.ItemPopularity;
import algorithms.MF_ALS;
import algorithms.MF_CD;
import algorithms.MF_fastALS;
import data_structure.Rating;

public class main_iter extends main {

	public static void main(String argv[]) throws IOException {
		String dataset_name = "testdatasmall";
		String method = "FastALS";
		double w0 = 10;
		boolean showProgress = true;
		boolean showLoss = true;
		int factors = 64;
		int maxIter = 5;
		double reg = 0.01;
		double alpha = 0.75;

		System.out.println("Start");
		Rating.userIds = parseUserName("data/" + dataset_name + ".rating");
		ReadRatings_HoldOneOut("data/" + dataset_name + ".rating");

		System.out.printf("%s: showProgress=%s, factors=%d, maxIter=%d, reg=%f, w0=%.2f, alpha=%.2f\n", method,
				showProgress, factors, maxIter, reg, w0, alpha);
		System.out.println("====================================================");

		ItemPopularity popularity = new ItemPopularity(trainMatrix, testRatings, topK, threadNum);
		evaluate_model(popularity, "Popularity");

		double init_mean = 0;
		double init_stdev = 0.01;

		if (method.equalsIgnoreCase("fastals")) {
			for ( int iter = 1; iter <= maxIter; iter++ ){
		    main.iterCount=iter; 
			MF_fastALS fals = new MF_fastALS(trainMatrix, testRatings, topK, threadNum, factors, iter, w0, alpha,
					reg, init_mean, init_stdev, showProgress, showLoss);

			evaluate_model(fals, "MF_fastALS");
			}
		}
		
		if (method.equalsIgnoreCase("als")) {
			for ( int iter = 1; iter <= maxIter; iter++ ){
				main.iterCount=iter; 	
			MF_ALS als = new MF_ALS(trainMatrix, testRatings, topK, threadNum,
					factors, iter, w0, reg, init_mean, init_stdev, showProgress, showLoss);
			evaluate_model(als, "MF_ALS");
			}
			
		}
		
		if (method.equalsIgnoreCase("cd")) {
			for ( int iter = 1; iter <= maxIter; iter++ ){
				main.iterCount=iter; 	
			MF_CD cd = new MF_CD(trainMatrix, testRatings, topK, threadNum,
					factors, iter, w0, reg, init_mean, init_stdev, showProgress, showLoss);
			evaluate_model(cd, "MF_CD");
			}
		}

	}

}
