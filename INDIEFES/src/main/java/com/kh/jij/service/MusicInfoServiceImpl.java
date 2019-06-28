package com.kh.jij.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.jij.domain.MusicInfoVo;
import com.kh.jij.persistence.IMusicInfoDao;

@Service
public class MusicInfoServiceImpl implements IMusicInfoService{

	private IMusicInfoDao musicDao;
	
	@Override
	public List<MusicInfoVo> musicRead(int art_number) throws Exception {
		List<MusicInfoVo> musicList = musicDao.musicRead(art_number);
		return musicList;
	}

	@Override
	public void musicInsert(MusicInfoVo musicInfoVo) throws Exception {
		musicDao.musicInsert(musicInfoVo);
	}
	
}