# SpaceTextWatcher
android输入框输入银行卡,输入手机,输入身份证格式化的实现
# 项目地址
[SpaceTextWatcher](https://github.com/Robin-jiangyufeng/SpaceTextWatcher)
# 实现方式
```java
    @Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		beforeTextLength = s.length();
		if (buffer.length() > 0) {
			buffer.delete(0, buffer.length());
		}
		spaceNumberA = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				spaceNumberA++;
			}
		}
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		onTextLength = s.length();
		buffer.append(s.toString());
		if (onTextLength == beforeTextLength || onTextLength > maxLenght
				|| isChanged) {
			isChanged = false;
			return;
		}
		isChanged = true;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (isChanged) {
			location = editText.getSelectionEnd();
			int index = 0;
			while (index < buffer.length()) { // 删掉所有空格
				if (buffer.charAt(index) == ' ') {
					buffer.deleteCharAt(index);
				} else {
					index++;
				}
			}

			index = 0;
			int spaceNumberB = 0;
			while (index < buffer.length()) { // 插入所有空格
				spaceNumberB = insertSpace(index, spaceNumberB);
				index++;
			}

			String str = buffer.toString();

			// 下面是计算光位置的
			if (spaceNumberB > spaceNumberA) {
				location += (spaceNumberB - spaceNumberA);
				spaceNumberA = spaceNumberB;
			}
			if (isSetText) {
				location = str.length();
				isSetText = false;
			} else if (location > str.length()) {
				location = str.length();
			} else if (location < 0) {
				location = 0;
			}

			editText.setText(str);
			try {
				editText.setSelection(location);
			} catch (Exception e) {
				e.printStackTrace();
			}
			isChanged = false;
		}
	}
	
    /**
	 * 根据类型插入空格
	 * 
	 * @param index
	 * @param spaceNumberAfter
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private int insertSpace(int index, int spaceNumberAfter) {
		switch (spaceType) {
		case defaultType:// 相隔四位空格
			if (index > 3
					&& (index % (4 * (spaceNumberAfter + 1)) == spaceNumberAfter)) {
				buffer.insert(index, ' ');
				spaceNumberAfter++;
			}
			break;
		case bankCardNumberType:
			if (index > 3
					&& (index % (4 * (spaceNumberAfter + 1)) == spaceNumberAfter)) {
				buffer.insert(index, ' ');
				spaceNumberAfter++;
			}
			break;
		case mobilePhoneNumberType:
			if (index == 3
					|| ((index > 7) && ((index - 3) % (4 * spaceNumberAfter) == spaceNumberAfter))) {
				buffer.insert(index, ' ');
				spaceNumberAfter++;
			}
			break;
		case IDCardNumberType:
			if (index == 6
					|| ((index > 10) && ((index - 6) % (4 * spaceNumberAfter) == spaceNumberAfter))) {
				buffer.insert(index, ' ');
				spaceNumberAfter++;
			}
			break;
		default:
			if (index > 3
					&& (index % (4 * (spaceNumberAfter + 1)) == spaceNumberAfter)) {
				buffer.insert(index, ' ');
				spaceNumberAfter++;
			}
			break;
		}
		return spaceNumberAfter;
	}

	/***
	 * 计算需要的空格数
	 * 
	 * @return 返回添加空格后的字符串长度
	 * @see [类、类#方法、类#成员]
	 */
	private int computeSpaceCount(CharSequence charSequence) {
		buffer.delete(0, buffer.length());
		buffer.append(charSequence.toString());
		int index = 0;
		int spaceNumberB = 0;
		while (index < buffer.length()) { // 插入所有空格
			spaceNumberB = insertSpace(index, spaceNumberB);
			index++;
		}
		buffer.delete(0, buffer.length());
		return index;
	}
```
# 使用方法
```java
        AddSpaceTextWatcher[] asEditTexts=new AddSpaceTextWatcher[3];
        EditText[] editTexts=new EditText[3];
        editTexts[0]=(EditText)findViewById(R.id.editText);//银行卡
        editTexts[1]=(EditText)findViewById(R.id.editText2);//手机号
        editTexts[2]=(EditText)findViewById(R.id.editText3);//身份证
        asEditTexts[0]=new AddSpaceTextWatcher(editTexts[0],48);//银行卡
        asEditTexts[0].setSpaceType(AddSpaceTextWatcher.SpaceType.bankCardNumberType);
        asEditTexts[1]=new AddSpaceTextWatcher(editTexts[1],13);//手机号
        asEditTexts[1].setSpaceType(AddSpaceTextWatcher.SpaceType.mobilePhoneNumberType);
        asEditTexts[2]=new AddSpaceTextWatcher(editTexts[2],21);//身份证
        asEditTexts[2].setSpaceType(AddSpaceTextWatcher.SpaceType.IDCardNumberType);
````

# 关于作者Robin
* 屌丝程序员
* GitHub: [Robin-jiangyufeng](https://github.com/Robin-jiangyufeng)
* QQ:429257411
* 交流QQ群 236395044