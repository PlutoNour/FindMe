package com.findme;


public class Word {

	private int id;
	private String word;
	private String level;


	public Word(int id, String word , String level)
	{
		this.id = id;
		this.word = word;
		this.level = level;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//

	public String getWord()
	{
		return word;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	//

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level=level;
	}

}
