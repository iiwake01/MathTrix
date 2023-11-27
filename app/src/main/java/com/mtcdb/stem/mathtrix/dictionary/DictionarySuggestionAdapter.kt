package com.mtcdb.stem.mathtrix.dictionary

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cursoradapter.widget.CursorAdapter

class DictionarySuggestionAdapter(context: Context, c: Cursor?) : CursorAdapter(context, c, 0) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context)
            .inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val termTextView = view?.findViewById<TextView>(android.R.id.text1)
        termTextView?.text = cursor?.getString(cursor.getColumnIndexOrThrow("term"))
    }

    override fun convertToString(cursor: Cursor): CharSequence {
        return cursor.getString(cursor.getColumnIndexOrThrow("term"))
    }
}