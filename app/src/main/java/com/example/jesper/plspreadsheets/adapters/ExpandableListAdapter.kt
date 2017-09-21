package com.example.jesper.plspreadsheets.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.*

/**
 * Adapter for an expandable list used in ViewActivity.kt
 *
 * @author Jesper Bergstrom
 * @name ExpandableListAdapter.kt
 * @version 0.00.00
 */
class ExpandableListAdapter(context: Context) : BaseExpandableListAdapter() {

    var groupNames = ArrayList<String>()
    var childNames = ArrayList<ArrayList<String>>()
    var context: Context? = null

    init{
        this.context = context
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, view: View?, parent: ViewGroup?): View {
        val txtView = TextView(context)
        txtView.text = groupNames[groupPosition]
        txtView.textSize = 22F
        return txtView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childNames[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childNames[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val txtView = TextView(context)
        txtView.text = childNames[groupPosition][childPosition]
        return txtView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return groupNames.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupNames[groupPosition]
    }
}
