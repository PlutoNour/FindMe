package com.findme;


public class Score {

	private int id;
	private String nickname;
	private String score;


	public Score(int id, String nickname , String score)
	{
		this.id = id;
		this.nickname = nickname;
		this.score = score;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	//

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score=score;
	}

}
