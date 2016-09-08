package savvato.tic_tac_toe.game;

import android.widget.ImageView;

import savvato.tic_tac_toe.MainActivity;

public class Game {
    private Field[][] GameFields = new Field[3][3];

    private byte currentPlayer = 0; // 0 => Toe, 1 => Cross

    public Game(ImageView[][] imageViews) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                GameFields[row][column] = new Field(imageViews[row][column], FieldState.Empty);
            }
        }
    }

    private void setFieldState(int row, int column, FieldState state) {
        this.GameFields[row][column].setState(state);
    }

    public void doStep(int row, int column) {
        if (this.currentPlayer == 0) {
            this.setFieldState(row, column, FieldState.Toe);
        }
        else {
            this.setFieldState(row, column, FieldState.Cross);
        }
        boolean win = this.checkWin();
        if (win) {
            MainActivity.blockFields();
            if (this.currentPlayer == 0) {
                MainActivity.makeToast("Нолики победили");
            }
            else {
                MainActivity.makeToast("Крестики победили");
            }
        }
        else {
            this.changePlayer();
        }
    }

    private boolean checkWin() {
        FieldState checkedState;
        if (this.currentPlayer == 0) {
            checkedState = FieldState.Toe;
        }
        else {
            checkedState = FieldState.Cross;
        }
        boolean win = false;
        //Check rows
        for (int row = 0; row < 3; row++) {
            win = true;
            for (int column = 0; column < 3; column++) {
                if (GameFields[row][column].getState() != checkedState) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return win;
            }
        }
        //Check columns
        for (int column = 0; column < 3; column++) {
            win = true;
            for (int row = 0; row < 3; row++) {
                if (GameFields[row][column].getState() != checkedState) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return win;
            }
        }
        //Check diagonals
        for (int row = 0; row < 3; row++) {
            int column = row;
            win = true;
            if (GameFields[row][column].getState() != checkedState) {
                win = false;
                break;
            }
        }
        if (win) {
            return win;
        }
        else {
            int column = 2;
            for (int row = 0; row < 3; row++) {
                win = true;
                if (GameFields[row][column].getState() != checkedState) {
                    win = false;
                    break;
                }
                column--;
            }
        }
        return win;
    }

    private void changePlayer() {
        if (this.currentPlayer == 0) {
            this.currentPlayer = 1;
        }
        else {
            this.currentPlayer = 0;
        }
    }
}
