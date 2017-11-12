package gui.obrazy;

import gui.wykresy.Wykres;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.List;

public class Kolor {

    public Kolor()
    {

    }

    public static Wykres znajdzWykresPoKolorze(List<Wykres> wykresy, Color c)
    {
        for (Wykres w:wykresy) {
            if(c.equals(w.getProvince().getKolorNaMapie()))
            {
                return w;
            }
        }
        return null;
    }

    public static Image polaczObrazy(Image docelowy, Image zrodlowy,int x, int y)
    {
        ImageView view = new ImageView(zrodlowy);
        view.setPreserveRatio(false);
        view.setFitWidth(50);
        view.setFitHeight(50);

        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);

        zrodlowy = view.snapshot(snapshotParameters,null);

        int width = (int) zrodlowy.getWidth();
        int height = (int) zrodlowy.getHeight();

        PixelReader pixelReader = zrodlowy.getPixelReader();
        WritableImage writableImage = new WritableImage(docelowy.getPixelReader(),(int)docelowy.getWidth(),(int)docelowy.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i=0;i<width;i++)
        {
            for (int j=0;j<height;j++)
            {
                Color c = pixelReader.getColor(j,i);
                int a = pixelReader.getArgb(j,i);
                if (a != 0)
                {
                    pixelWriter.setColor(x + j, y + i, c);
                    pixelWriter.setArgb(x + j, y + i, a);
                }
            }
        }
        return writableImage;
    }
}

