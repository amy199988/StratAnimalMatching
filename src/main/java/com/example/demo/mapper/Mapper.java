package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.DonationDto;
import com.example.demo.model.dto.DonationInventoryDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.NoticeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.AdoptionRequest;
import com.example.demo.model.entity.Cat;
import com.example.demo.model.entity.Donation;
import com.example.demo.model.entity.DonationInventory;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.model.entity.Notice;
import com.example.demo.model.entity.ReportList;
import com.example.demo.model.entity.User;

@Component
public class Mapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto toUserDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	public User toUserEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
	
	public CatDto toCatDto(Cat cat) {
		return modelMapper.map(cat, CatDto.class);
	}
	
	public Cat toCatEntity(CatDto catDto) {
		return modelMapper.map(catDto, Cat.class);
	}
	
	public AdoptionRequestDto toAdoptionRequestDto(AdoptionRequest adoptionRequest) {
		return modelMapper.map(adoptionRequest, AdoptionRequestDto.class);
	}
	
	public AdoptionRequest toAdoptionRequestEntity(AdoptionRequestDto adoptionRequestDto) {
		return modelMapper.map(adoptionRequestDto, AdoptionRequest.class);
	}
	
	public DonationDto toDonationDto(Donation donation) {
		return modelMapper.map(donation, DonationDto.class);
	}
	
	public Donation toDonationEntity(DonationDto donationDto) {
		return modelMapper.map(donationDto, Donation.class);
	}
	
	public DonationInventoryDto toDonationInventoryDto(DonationInventory donationInventory) {
		return modelMapper.map(donationInventory, DonationInventoryDto.class);
	}
	
	public DonationInventory toDonationInventoryEntity(DonationInventoryDto donationInventoryDto) {
		return modelMapper.map(donationInventoryDto, DonationInventory.class);
	}
	
	public LovehomeDto toLovehomeDto(Lovehome lovehome) {
		return modelMapper.map(lovehome, LovehomeDto.class);
	}
	
	public Lovehome toLovehomeEntity(LovehomeDto lovehomeDto) {
		return modelMapper.map(lovehomeDto, Lovehome.class);
	}
	
	public NoticeDto toNoticeDto(Notice notice) {
		return modelMapper.map(notice, NoticeDto.class);
	}
	
	public Notice toNoticeEntity(NoticeDto noticeDto) {
		return modelMapper.map(noticeDto, Notice.class);
	}
	
	public ReportListDto toReportListDto(ReportList reportList) {
		return modelMapper.map(reportList, ReportListDto.class);
	}
	
	public ReportList toReportListEntity(ReportListDto reportListDto) {
		return modelMapper.map(reportListDto, ReportList.class);
	}
}
