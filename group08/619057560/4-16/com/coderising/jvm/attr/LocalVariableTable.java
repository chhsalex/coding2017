package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		LocalVariableTable table = new LocalVariableTable(attrNameIndex, attrLen);
		
		int tableLen = iter.nextU2ToInt();
		for (int i = 1; i <= tableLen; i++) {
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.nextU2ToInt());
			item.setLength(iter.nextU2ToInt());
			item.setNameIndex(iter.nextU2ToInt());
			item.setDescIndex(iter.nextU2ToInt());
			item.setIndex(iter.nextU2ToInt());
			table.addLocalVariableItem(item);
		}
		return table;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
