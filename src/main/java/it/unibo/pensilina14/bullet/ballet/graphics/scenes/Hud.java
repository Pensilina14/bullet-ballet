package it.unibo.pensilina14.bullet.ballet.graphics.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Hud {

  private final HudLabels hudLabel;
  private final Pos pos;
  private final ContentDisplay contentDisplay;
  private final Pane pane;
  private final Insets insets;

  public Hud(
      final HudLabels hudLabel,
      final Pos pos,
      final ContentDisplay contentDisplay,
      final Pane pane,
      final Insets insets) {
    this.hudLabel = hudLabel;
    this.pos = pos;
    this.contentDisplay = contentDisplay;
    this.pane = pane;
    this.insets = insets;
    this.setup();
  }

  private void setup() {
    final Label label = new Label(hudLabel.toString());
    label.setId(hudLabel.toString());
    label.setAlignment(pos);
    label.setContentDisplay(contentDisplay);
    label.setTextFill(Color.RED);
    label.setFont(Font.font(24));
    label.setPadding(Insets.EMPTY);
    this.pane.getChildren().add(label);
    StackPane.setMargin(label, insets); // top, right, down, left
    StackPane.setAlignment(label, pos);
  }

  public HudLabels getID() {
    return this.hudLabel;
  }

  public void modifyLabel(final Label label) {
    this.pane.getChildren();
  }
}
