/*
 * Copyright 2020-2020 the ALttPJ Team @ https://github.com/alttpj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.alttpj.memeforcehunt.app.gui.editor;

import io.github.alttpj.library.image.SnesTileUnpacker;
import io.github.alttpj.library.image.Tile;

import java.util.List;

public class Painter {

  private final SnesTileUnpacker unpacker = new SnesTileUnpacker();

  public void paint(final PaintableGrid paintableGrid, final Tile[] tiles,
                    final ColorSelector colorSelector) {
    if (tiles.length != 4) {
      throw new IllegalArgumentException("Only four tiles allowed at the moment, got " + tiles.length + " instead.");
    }

    final List<ColorSelectorCell> colorSelectorCells = colorSelector.getColorSelectorCells();

    for (final Tile tile : tiles) {
      final byte[] tileBytes = this.unpacker.unpack3bppTiles(tile.getBytes());
      for (int byteIndex = 0; byteIndex < tileBytes.length; byteIndex++) {
        final int row = byteIndex / 8;
        final int column = byteIndex % 8;
        paintableGrid.getCell(row, column).paint(colorSelectorCells.get(tileBytes[byteIndex]));
      }
    }
  }
}
