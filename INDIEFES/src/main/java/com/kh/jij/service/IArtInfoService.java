package com.kh.jij.service;


import java.util.Map;
import java.util.List;

import com.kh.jij.domain.ArtInfoVo;
import com.kh.jij.domain.IndieTeamVo;
import com.kh.jij.domain.TeamMemberVo;
import com.kh.jij.domain.MusicInfoVo;

public interface IArtInfoService {
	// 글쓰기
	public void insert(ArtInfoVo vo) throws Exception;
	// 팀생성
	public void teamInsert(Map<String, Object> map) throws Exception;
	// 팀가입
	public void teamInput(TeamMemberVo vo) throws Exception;
	
	// 파일경로 인서트(첨부)
	public void attach(String file_path) throws Exception;
	
	// 앨범 정보 가져오기
	public ArtInfoVo artRead(int art_number) throws Exception;
	
	// 앨범 정보 가져오기
	public ArtInfoVo artModify(String user_id, int art_number) throws Exception;
	
	// 노래 정보 가져오기
	public List<MusicInfoVo> musicRead(int art_number) throws Exception;
	
	// 앨범 리스트 가져오기
	public List<ArtInfoVo> allArtList() throws Exception;
	public List<IndieTeamVo> getIndieTeam() throws Exception;
	// 앨범 커버 리스트 가져오기
	public List<String> getCover();
	
	public int getIndieNumber(String user_id) throws Exception;
	public String getTeamName (int team_number) throws Exception;
}
