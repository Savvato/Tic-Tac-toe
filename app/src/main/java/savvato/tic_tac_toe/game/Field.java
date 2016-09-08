package savvato.tic_tac_toe.game;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Field
{
    private static Drawable NullResource;
    private static Drawable CrossResource;
    private static Drawable ToeResource;

    private ImageView view;
    private FieldState state;

    public Field(ImageView view, FieldState state) {
        this.view = view;
        this.state = state;
        this.initializeStateOnView();
    }

    public static void initializeResources(Drawable Null, Drawable Cross, Drawable Toe) {
        Field.NullResource = Null;
        Field.CrossResource = Cross;
        Field.ToeResource = Toe;
    }

    private void initializeStateOnView() {
        switch (this.state) {
            case Empty:
                this.view.setImageDrawable(NullResource);
                break;
            case Cross:
                this.view.setImageDrawable(CrossResource);
                break;
            case Toe:
                this.view.setImageDrawable(ToeResource);
                break;
        }
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        this.state = state;
        this.initializeStateOnView();
        this.view.setClickable(false);
    }
}
