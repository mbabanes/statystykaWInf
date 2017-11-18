package gui.controllers;

import entity.provinces.Province;
import gui.obrazy.Kolor;
import gui.wykresy.Wykres;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class WykresController {


    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView img;
    @FXML
    private ObservableList<Province> wojewodztwa;
    private Stage stage;
    @FXML
    private Button btn;
    @FXML
    private HBox hbox;
    private Image mapaCala;
    private Image mapaOryginal;
    @FXML
    private ImageView imageView;
    private List<Wykres> wykresy;

    @FXML
    public void initialize() {
    }

    public WykresController() {

    }

    public void aktualizujWykres(int index) {
        mapaCala = new Image("/img/polska_nowa_szara_legenda.png");

        //czysci
        wykresy.get(index).czyscWykres();

        //dodaje zmienne do wykresu
        wykresy.get(index).dodajWartoscDoWykresuPonownie();

        //generuje wykresy
        wykresy.get(index).ustawWykres();
        wykresy.get(index).ustawStyl("style.css");
        wykresy.get(index).getBarChart().setPrefHeight(600);
        wykresy.get(index).getBarChart().setPrefWidth(800);

        //ustawia dwa najwieksze plony dla wykresu
        wykresy.get(index).ustawNajwiekszeWartosci();

        //dodanie plonów na mapie głównej
        ustawPlony();


        imageView.setImage(mapaCala);

        //inicjowanie kolorow dla poszczegolnego wojewodztwa
//        Kolor.ustawKoloryPierwotne(mapaCala, wojewodztwa);
        pomalujWojewodztwa();
    }

    public void dajWykres() {
        wykresy = new ArrayList<>();
//        mapaCala = new Image("/img/polskaKolorowa.png");
        mapaCala = new Image("/img/polska_nowa_szara_legenda.png");

        //inicjuje liste wykresow
        for (Province p : wojewodztwa) {
            Wykres w = new Wykres(p.getName());
            w.setProvince(p);
            wykresy.add(w);
        }

        //dodaje zmienne do wykresu
        int i = 0;
        for (Province p : wojewodztwa) {
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getBarley().getName(), p.getBarley().getQuantity());
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getSugarBeets().getName(), p.getSugarBeets().getQuantity());
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getWheat().getName(), p.getWheat().getQuantity());
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getOat().getName(), p.getOat().getQuantity());
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getPotatoes().getName(), p.getPotatoes().getQuantity());
            wykresy.get(i).dodajWartoscDoWykresu(i, p.getRye().getName(), p.getRye().getQuantity());
            i++;
        }

        //generuje wykresy
        for (Wykres wp : wykresy) {
            wp.ustawWykres();
            wp.ustawStyl("style.css");
            wp.getBarChart().setPrefHeight(600);
            wp.getBarChart().setPrefWidth(800);
        }

        //rozdzielczosc imageView musi byc taka sama jak obrazu(image), bo inaczej to sie wszystko pierdzieli
        imageView.setImage(mapaCala);
        imageView.setFitWidth(mapaCala.getWidth());
        imageView.setFitHeight(mapaCala.getHeight());
        imageView.setPreserveRatio(true);

        //inicjowanie kolorow dla poszczegolnego wojewodztwa
        Kolor.ustawKoloryPierwotne(mapaCala, wojewodztwa);

        //ustawia dwa najwieksze plony dla wykresu
        for (Wykres w : wykresy) {
            w.ustawNajwiekszeWartosci();
        }

        //dodanie plonów na mapie głównej
        ustawPlony();

        imageView.setImage(mapaCala);

        pomalujWojewodztwa();
    }

    private int xy(int width, double procent) {
        return (int) ((width * procent) / 100);
    }

    private void ustawPlony() {
        int width = (int) mapaCala.getWidth();
        int height = (int) mapaCala.getHeight();


        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(0).getTop1(), xy(width, 15), xy(height, 60));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(0).getTop2(), xy(width, 20), xy(height, 60));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(1).getTop1(), xy(width, 38), xy(height, 28));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(1).getTop2(), xy(width, 43), xy(height, 28));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(2).getTop1(), xy(width, 80), xy(height, 55));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(2).getTop2(), xy(width, 85), xy(height, 55));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(3).getTop1(), xy(width, 6), xy(height, 40));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(3).getTop2(), xy(width, 11), xy(height, 40));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(4).getTop1(), xy(width, 47), xy(height, 50));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(4).getTop2(), xy(width, 52), xy(height, 50));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(5).getTop1(), xy(width, 56), xy(height, 80));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(5).getTop2(), xy(width, 61), xy(height, 80));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(6).getTop1(), xy(width, 62), xy(height, 40));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(6).getTop2(), xy(width, 67), xy(height, 40));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(7).getTop1(), xy(width, 32), xy(height, 65)); //opol
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(7).getTop2(), xy(width, 37), xy(height, 65));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(8).getTop1(), xy(width, 73), xy(height, 75)); //podk
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(8).getTop2(), xy(width, 78), xy(height, 75));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(9).getTop1(), xy(width, 80), xy(height, 25));//podl
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(9).getTop2(), xy(width, 85), xy(height, 25));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(10).getTop1(), xy(width, 30), xy(height, 10));//pomorsk
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(10).getTop2(), xy(width, 35), xy(height, 10));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(11).getTop1(), xy(width, 40), xy(height, 72));//slaskie
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(11).getTop2(), xy(width, 45), xy(height, 72));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(12).getTop1(), xy(width, 60), xy(height, 65));//swietokrzy
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(12).getTop2(), xy(width, 65), xy(height, 65));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(13).getTop1(), xy(width, 60), xy(height, 15));//warm
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(13).getTop2(), xy(width, 65), xy(height, 15));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(14).getTop1(), xy(width, 25), xy(height, 40)); //wlkp
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(14).getTop2(), xy(width, 30), xy(height, 40));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(15).getTop1(), xy(width, 10), xy(height, 18));
        mapaCala = Kolor.polaczObrazy(mapaCala, wykresy.get(15).getTop2(), xy(width, 15), xy(height, 18));
    }


    public void zmienKolorWojewodztwa(int index) {
        final double przelicznik = 6.35;

        int green = (int) (255 - (przelicznik * wojewodztwa.get(index).areaProperty().get()));

        if (green < 0)
            green = 0;

        if (green > 255)
            green = 255;

        Color naKolor = Color.rgb(255, green, index);

        mapaCala = Kolor.zmienKolor(mapaCala, wojewodztwa.get(index).getKolorSzary(), naKolor);
        mapaCala = Kolor.zmienKolor(mapaCala, wojewodztwa.get(index).getKolorNaMapie(), naKolor);

        imageView.setImage(mapaCala);
        wojewodztwa.get(index).setKolorNaMapie(naKolor);

    }

    public void pomalujWojewodztwa() {
        for (int i = 0; i < 16; i++) {
            zmienKolorWojewodztwa(i);
        }
    }

    @FXML
    public void mouseEventMapa(MouseEvent event) {
        //rozpoznanie koloru i pokazanie wykresu
        PixelReader pr = mapaCala.getPixelReader();
        Color c = pr.getColor((int) event.getX(), (int) event.getY());


        Wykres wykres = Kolor.znajdzWykresPoKolorze(wykresy, c);
        if (wykres != null) {

            if (hbox.getChildren().size() > 1)
                hbox.getChildren().remove(1);

            hbox.getChildren().add(wykres.getBarChart());
        }
    }

    public ObservableList<Province> getWojewodztwa() {
        return wojewodztwa;
    }

    public void setWojewodztwa(ObservableList<Province> wojewodztwa) {
        this.wojewodztwa = wojewodztwa;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void initKolory() {
        wojewodztwa.get(0).setKolorNaMapie(new Color(0.24313725531101227, 0.9764705896377563, 0.27843138575553894, 1.0));
        wojewodztwa.get(1).setKolorNaMapie(new Color(0.7450980544090271, 0.9921568632125854, 0.3764705955982208, 1.0));
        wojewodztwa.get(2).setKolorNaMapie(new Color(0.8823529481887817, 0.8549019694328308, 0.3333333432674408, 1.0));
        wojewodztwa.get(3).setKolorNaMapie(new Color(0.6705882549285889, 0.7882353067398071, 0.0117647061124444, 1.0));
        wojewodztwa.get(4).setKolorNaMapie(new Color(1.0, 0.7882353067398071, 0.054901961237192154, 1.0));
        wojewodztwa.get(5).setKolorNaMapie(new Color(0.8705882430076599, 0.9215686321258545, 0.9215686321258545, 1.0));
        wojewodztwa.get(6).setKolorNaMapie(new Color(0.3764705955982208, 1.0, 0.21568627655506134, 1.0));
        wojewodztwa.get(7).setKolorNaMapie(new Color(0.062745101749897, 0.7372549176216125, 0.7058823704719543, 1.0));
        wojewodztwa.get(8).setKolorNaMapie(new Color(0.7647058963775635, 0.7647058963775635, 0.7647058963775635, 1.0));
        wojewodztwa.get(9).setKolorNaMapie(new Color(0.7686274647712708, 0.7058823704719543, 0.45098039507865906, 1.0));
        wojewodztwa.get(10).setKolorNaMapie(new Color(0.501960813999176, 1.0, 0.501960813999176, 1.0));
//        wojewodztwa.get(11).setKolorNaMapie(new Color(0.29411765933036804, 0.843137264251709, 0.8196078538894653, 1.0));
        wojewodztwa.get(11).setKolorNaMapie(Color.valueOf("0xe8f791ff"));
        //swie
        wojewodztwa.get(12).setKolorNaMapie(new Color(0.3686274588108063, 0.6078431606292725, 0.8509804010391235, 1.0));
        wojewodztwa.get(13).setKolorNaMapie(new Color(0.9450980424880981, 0.9529411792755127, 0.545098066329956, 1.0));
        wojewodztwa.get(14).setKolorNaMapie(new Color(0.9803921580314636, 0.5686274766921997, 0.5529412031173706, 1.0));
        wojewodztwa.get(15).setKolorNaMapie(new Color(0.545098066329956, 0.8745098114013672, 0.9725490212440491, 1.0));
    }


}
