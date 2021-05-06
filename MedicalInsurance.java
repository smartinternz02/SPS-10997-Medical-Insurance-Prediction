package ml;

import java.io.IOException;
import java.util.Arrays;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BoxTrace;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@SuppressWarnings("unused")
public class MedicalInsurance {
	public static Instances getInstances (String filename)
	{
		
		DataSource source;
		Instances dataset = null;
		try {
			source = new DataSource(filename);
			dataset = source.getDataSet();
			dataset.setClassIndex(dataset.numAttributes()-1);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return dataset;
	}
	
	public static void main(String args[]) {
		System.out.println("data analytics");
		try {
		Table insurance_data=Table.read().csv("C:\\Users\\sreej\\Downloads\\dataset2.csv");
		
		System.out.println(insurance_data.shape());
		System.out.println(insurance_data.structure());
		System.out.println(insurance_data.summary());
		Layout l1=Layout.builder().title("age distribution").build();
		//box-plot
		BoxTrace t1=BoxTrace.builder(insurance_data.categoricalColumn("smoker=no"), insurance_data.nCol("charges")).build();
		Plot.show(new Figure(l1,t1));
		//scatter-plot
		ScatterTrace s=ScatterTrace.builder(insurance_data.nCol("bmi"),insurance_data.nCol("charges")).build();
		Plot.show(new Figure(l1,s));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Instances train_data = getInstances("C:\\Users\\sreej\\OneDrive\\Desktop\\train.arff");
		Instances test_data = getInstances("C:\\Users\\sreej\\OneDrive\\Desktop\\test.arff");
		System.out.println(train_data.size());
		
		/** Classifier here is Linear Regression */
		Classifier classifier = new weka.classifiers.functions.Logistic();
		/** */
		try {
			classifier.buildClassifier(train_data);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		Evaluation eval;
		try {
			eval = new Evaluation(train_data);
			eval.evaluateModel(classifier, test_data);
			System.out.println("** Logistic Regression Evaluation with Datasets **");
			System.out.println(eval.toSummaryString());
			System.out.print(" the expression for the input data as per alogorithm is ");
			System.out.println(classifier);
			
			double confusion[][] = eval.confusionMatrix();
			System.out.println("Confusion matrix:");
			for (double[] row : confusion)
				System.out.println(	 Arrays.toString(row));
			System.out.println("-------------------");

			System.out.println("Area under the curve");
			System.out.println( eval.areaUnderROC(0));
			System.out.println("-------------------");
			
			System.out.println(eval.getAllEvaluationMetricNames());
			
			System.out.print("Recall :");
			System.out.println(Math.round(eval.recall(1)*100.0)/100.0);
			
			System.out.print("Precision:");
			System.out.println(Math.round(eval.precision(1)*100.0)/100.0);
			System.out.print("F1 score:");
			System.out.println(Math.round(eval.fMeasure(1)*100.0)/100.0);
			
			System.out.print("Accuracy:");
			double acc = eval.correct()/(eval.correct()+ eval.incorrect());
			System.out.println(Math.round(acc*100.0)/100.0);
			
			
			System.out.println("-------------------");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/** Print the algorithm summary */
		
		Instance predicationDataSet = test_data.get(2);
		double value;
		try {
			value = classifier.classifyInstance(predicationDataSet);
			System.out.println("Predicted label:");
			System.out.print(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** Prediction Output */
		
		
	}

}

