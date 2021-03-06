package com.fumagalapps.tennisboard.Helper

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.adapter.DragAdaptadorGolpes
import kotlinx.android.synthetic.main.item_lista_golpes.view.*
import kotlin.coroutines.coroutineContext

class MyItemTouchHelperCallBack(
    private val adaptador: IItemTouchHelperAdaptador,
    private val context: Context
) :
    ItemTouchHelper.Callback() {
    companion object {
        const val ALPHA_FULL = 1.0f
    }

    //Ctrl+O
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags =
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or
                        ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (viewHolder.itemViewType != target.itemViewType)
            return false
        adaptador.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adaptador.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = ALPHA_FULL - Math.abs(dX) / viewHolder.itemView.width.toFloat()
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.translationX = dX

        } else
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.alpha = ALPHA_FULL
        if (viewHolder is IItemTouchHelperVistaHolder) {
            val itemViewHolder = viewHolder as IItemTouchHelperVistaHolder
            itemViewHolder.onItemLimpiar()
        }
        var xx: RecyclerView.ViewHolder?
        var ordenBoton: String = ""

        for (i in 0..recyclerView.adapter!!.itemCount - 1) {
            xx = recyclerView.findViewHolderForAdapterPosition(i)
            ordenBoton = ordenBoton + xx!!.itemView.txv_ClaveGolpe.text.toString()
        }

        var sp: SharedPreferences =
            context.getSharedPreferences("TennisBoardPreferences", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sp.edit()
        editor.putString("ordenBotones",ordenBoton)
        editor.commit()


    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is IItemTouchHelperVistaHolder) {
                val itemViewHolder = viewHolder as IItemTouchHelperVistaHolder
                itemViewHolder.onItemSeleccionado()
            }

        }
        super.onSelectedChanged(viewHolder, actionState)
    }


}