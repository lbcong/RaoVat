/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import ManageBean.Model.UserData;

import java.util.List;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Asus
 */
public class Chart {

    private BarChartModel barModel;
    private List<UserData> listUserData = null;

    /**
     * Creates a new instance of Chart
     */
    public Chart(List<?> model) {
        this.listUserData = (List<UserData>) model;
        barModel = initBarModel();
        barModel.setTitle("Biểu Đồ Thống Kê");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Thành Viên");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Số Bài");
        int m = 0;
        for (int i = 1; i < listUserData.size(); i++) {

            if (listUserData.get(m).getListpostOnDate().size() < listUserData.get(i).getListpostOnDate().size()) {
                m = i;
            }

        }

        yAxis.setMin(0);
        yAxis.setMax(listUserData.get(m).getListpostOnDate().size() + 5);

    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Tin đăng");
        for (int i = 0; i < listUserData.size(); i++) {
            boys.set(listUserData.get(i).getMember().getUsername(), listUserData.get(i).getListpostOnDate().size());

        }

        model.addSeries(boys);

        return model;
    }

}
