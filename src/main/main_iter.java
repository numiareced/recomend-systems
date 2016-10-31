package main;

import java.io.IOException;

import algorithms.ItemPopularity;
import algorithms.MF_ALS;
import algorithms.MF_CD;
import algorithms.MF_fastALS;
import algorithms.MFbpr;
import data_structure.Rating;

public class main_iter extends main {

	public static void main(String argv[]) throws IOException {
		String dataset_name = "newdata";
		String method = "bpr";
		double w0 = 10;
		boolean showProgress = true;
		boolean showLoss = true;
		int factors = 64;
		int maxIter = 600;
		double reg = 0.01;
		double alpha = 0.75;
		int interval = 1000;
		String onlineMode = "ui";
		System.out.println("Start");
		parseUserName("data/" + dataset_name + ".rating");
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
		
		
		if (method.equalsIgnoreCase("bpr")) {
			for ( int iter = 72; iter <= maxIter; iter++ ){
			MFbpr bpr = new MFbpr(trainMatrix, testRatings, topK, threadNum, 
					factors, iter, w0, false, reg, init_mean, init_stdev, showProgress);
			bpr.onlineMode = onlineMode;
			bpr.buildModel();
			bpr.maxIterOnline = iter;
			evaluate_model_online(bpr, "BPR", interval);
			}
		}

	}

}
