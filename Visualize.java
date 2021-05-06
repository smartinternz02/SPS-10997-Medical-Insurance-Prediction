package ml;

import java.io.IOException;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BoxTrace;
import tech.tablesaw.plotly.traces.HistogramTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;

public class Visualize {
	
	public static void main(String args[]) {
		System.out.println("data analytics");
		try {
		Table insurance_data=Table.read().csv("C:\\Users\\sreej\\Downloads\\dataset2.csv");
		System.out.println(insurance_data.shape());
		System.out.println(insurance_data.structure());
		System.out.println(insurance_data.summary());
		Layout l1=Layout.builder().title("Medical Insurance").build();
		//box-plot
		BoxTrace t1=BoxTrace.builder(insurance_data.categoricalColumn("smoker=no"), insurance_data.nCol("charges")).build();
		//histogram
		HistogramTrace t2=HistogramTrace.builder(insurance_data.categoricalColumn("children"), insurance_data.nCol("charges")).build();
		Plot.show(new Figure(l1,t1));
		Plot.show(new Figure(l1,t2));
		//scatter-plot
		ScatterTrace s=ScatterTrace.builder(insurance_data.nCol("bmi"),insurance_data.nCol("charges")).build();
		Plot.show(new Figure(l1,s));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
