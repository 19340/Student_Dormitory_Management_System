package sdms;

import javax.swing.text.*;

public class NumLimit extends PlainDocument { //�����ı���ֻ����������
	public NumLimit() {
		super();
	}
	public void insertString(int offset,String str,AttributeSet a) throws BadLocationException {
		if(str==null) return;
		char[] s=str.toCharArray();
		int length=0;
		for (int i=0;i<s.length;i++) {
            if ((s[i]>='0')&&(s[i]<='9')) {
                s[length++]=s[i];
            }
            super.insertString(offset,new String(s,0,length),a);
		}
	}
}