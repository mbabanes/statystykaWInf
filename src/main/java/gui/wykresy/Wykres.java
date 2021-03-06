package gui.wykresy;

import entity.plants.Plant;
import entity.provinces.Province;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wykres {

    private BarChart<String,Number>  chart;
    private CategoryAxis x;
    private NumberAxis y;
    private XYChart.Series<String,Number> seria;
    private String nazwa;
    private Province province;
    private List<Plant> listaPlants;
    private Image top1;
    private Image top2;

    public Wykres()
    {
        x = new CategoryAxis();
        y = new NumberAxis();

        x.setLabel("Plon");
        y.setLabel("Zbiór w dt");
        chart = new BarChart<String, Number>(x,y);
        seria = new XYChart.Series<>();
        listaPlants = new ArrayList<>();
    }

    public Wykres(String nazwa)
    {
        this();
        this.nazwa = nazwa;
        this.chart.setTitle(nazwa);
        this.chart.setTitleSide(Side.TOP);
        this.chart.setLegendVisible(true);
        this.chart.setAnimated(false);
        this.chart.setId("myBar");
    }

    public void dodajWartoscDoWykresu(int index, String kategoria,Number wartosc)
    {
        seria.getData().add(new XYChart.Data<>(kategoria,wartosc));
    }

    public void dodajWartoscDoWykresuPonownie()
    {
        dodajWartoscDoWykresu(0,province.getBarley().getName(),province.getBarley().getQuantity());
        dodajWartoscDoWykresu(0,province.getSugarBeets().getName(),province.getSugarBeets().getQuantity());
        dodajWartoscDoWykresu(0,province.getWheat().getName(),province.getWheat().getQuantity());
        dodajWartoscDoWykresu(0,province.getOat().getName(),province.getOat().getQuantity());
        dodajWartoscDoWykresu(0,province.getPotatoes().getName(),province.getPotatoes().getQuantity());
        dodajWartoscDoWykresu(0,province.getRye().getName(),province.getRye().getQuantity());
    }

    public void czyscWykres()
    {
        chart.getData().clear();
        seria.getData().clear();
    }

    public void ustawWykres()
    {
        chart.getData().add(seria);
        opcjeWykresu();
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BarChart<String,Number> getBarChart()
    {
        return chart;
    }

    public XYChart.Series<String,Number> getSeria()
    {
        return seria;
    }

    private void opcjeWykresu()
    {
        chart.applyCss();
        chart.layout();
        chart.setLegendVisible(false);
        chart.setCenterShape(true);
        chart.setManaged(true);
    }

    private void opcjeSerii()
    {

    }

    public void zapiszWykres(String sciezka)
    {
        SnapshotParameters pm = new SnapshotParameters();
//        pm.setTransform(Transform.scale(0.3,0.3));

        WritableImage image = chart.snapshot(pm,null);


        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",new File(sciezka));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WritableImage dajObraz()
    {
        SnapshotParameters pm = new SnapshotParameters();
        pm.setTransform(Transform.scale(1,1));



        return chart.snapshot(pm,null);
    }

    public void ustawStyl(String st)
    {
        this.chart.getStylesheets().add(st);
    }

    public String dajStyl()
    {
        return this.chart.getStyle().toString();
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
        initPlants();
    }

    public void ustawNajwiekszeWartosci()
    {
        long max = 0;
        long max2 = 0;
        Plant pk = null;
        Plant pk2 = null;

        for (Plant p: listaPlants) {
            if(p.getQuantity()>max)
            {
                max = p.getQuantity();
                pk = p;
            }
        }

        for (Plant p: listaPlants) {
            if(p.getQuantity()>max2)
            {
                if(p.getQuantity()!=max) {
                    max2 = p.getQuantity();
                    pk2 = p;
                }
            }
        }

        String sc1 = "";

        if(pk != null) {
            sc1 = pk.getImagePath();
            top1 = new Image(sc1);
        }
        else
            top1 = new Image("/img/transparent.png");

        if(pk2 != null) {
            sc1 = pk2.getImagePath();
            top2 = new Image(sc1);
        }
        else
            top2 = new Image("/img/transparent.png");
    }

    public Image getTop1() {

        return top1;
    }

    public Image getTop2() {
        return top2;
    }

    private void initPlants()
    {
        listaPlants.add(province.getBarley());
        listaPlants.add(province.getOat());
        listaPlants.add(province.getPotatoes());
        listaPlants.add(province.getRye());
        listaPlants.add(province.getSugarBeets());
        listaPlants.add(province.getWheat());
    }
}

