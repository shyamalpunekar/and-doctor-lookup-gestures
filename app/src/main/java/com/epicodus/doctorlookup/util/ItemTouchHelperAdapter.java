package com.epicodus.doctorlookup.util;

/**
 * Created by spunek on 11/2/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
