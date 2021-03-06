//package com.example.foodmap.service;
//
//import com.example.foodmap.dto.meeting.MeetingCreatRequestDto;
//import com.example.foodmap.dto.meeting.MeetingDetailResponseDto;
//import com.example.foodmap.dto.meeting.MeetingTotalListResponseDto;
//import com.example.foodmap.model.*;
//import com.example.foodmap.repository.MeetingCommentRepository;
//import com.example.foodmap.repository.MeetingParticipateRepository;
//import com.example.foodmap.repository.MeetingRepository;
//import com.example.foodmap.repository.UserRepository;
//import com.example.foodmap.scheduler.MeetingScheduler;
//import com.example.foodmap.security.UserDetailsImpl;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class MeetingServiceTest {
//
//    @InjectMocks
//    MeetingService meetingService;
//    @Mock
//    MeetingRepository meetingRepository;
//    @Mock
//    UserRepository userRepository;
//    @Mock
//    MeetingParticipateRepository meetingParticipateRepository;
//    @Mock
//    MeetingCommentRepository meetingCommentRepository;
//
//
//
//
//    private Meeting meeting;
//    private User user1;
//    private User user2;
//    private MeetingCreatRequestDto meetingCreatRequestDto;
//    private UserDetailsImpl userDetails;
//    private String meetingTitle;
//    private LocalDateTime startDate;
//    private String restaurant;
//    private LocalDateTime endDate;
//    private LocalDateTime meetingDate;
//    private Location location;
//    private int limitPeople;
//    private int nowPeople;
//    private String content;
//
//
//    @BeforeEach
//    void setup(){
//       location = new Location("?????????",123.231,12.234);
//       user1 = new User(
//                "?????????",
//                "asdf1234",
//                222L,
//                "hanghae99@naver.com",
//                UserRoleEnum.USER,
//                1L,
//               "http://sljlet.com",
//               location,
//               "??????"
//        );
//
//
//        meetingTitle="??????????????? ??????????";
//        startDate = LocalDateTime.of(2021,12,24,05,00);
//        endDate = LocalDateTime.of(2021,12,25,12,00);
//        meetingDate = LocalDateTime.of(2021,12,28,14,00);
//        restaurant="???????????????";
//        limitPeople=5;
//        nowPeople=1;
//        content= "?????????";
//
//
//        meetingCreatRequestDto = new MeetingCreatRequestDto(
//                meetingTitle,startDate,restaurant,endDate,meetingDate,location,limitPeople,nowPeople,content
//        );
//
//        //user1 ?????? ??????
//        meeting = new Meeting(user1,meetingCreatRequestDto);
//        //user1 ?????? ??????
//        userDetails = new UserDetailsImpl(user1);
//
//    }
//    @Test
//    @DisplayName("????????????")
//    void create(){
//        //given
//        when(userRepository.findByKakaoId(user1.getKakaoId()))
//                .thenReturn(Optional.of(user1));
//
//        //when-then
//        meetingService.creatMeeting(meetingCreatRequestDto,userDetails);
//
//     }
//    @Test
//    @DisplayName("???????????? - ???????????? null??????")
//    void create1(){
//        //given
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.creatMeeting(meetingCreatRequestDto,userDetails));
//        //then
//        assertThat(exception.getMessage()).isEqualTo("???????????? ???????????????.");
//
//
//    }
//    @Test
//    @DisplayName("????????????")
//    void getMeeting(){
//        //given
//        when(userRepository.findByKakaoId(user1.getKakaoId()))
//                .thenReturn(Optional.of(user1));
//        when(meetingRepository.findById(meeting.getId()))
//                .thenReturn(Optional.of(meeting));
//
//
//
//        //when
//        MeetingDetailResponseDto meetingDetailResponseDto = meetingService.getMeeting(meeting.getId(),userDetails);
//
//        //then
//
//        assertEquals(user1.getId(),meetingDetailResponseDto.getMeetingInfo().getUserId());
//        assertEquals(meeting.getViewCount(),meetingDetailResponseDto.getMeetingInfo().getViewCount());
//        assertEquals(meeting.getMeetingTitle(),meetingDetailResponseDto.getMeetingInfo().getMeetingTitle());
//        assertEquals(meeting.getStartDate(),meetingDetailResponseDto.getMeetingInfo().getStartDate());
//        assertEquals(meeting.getViewCount(),meetingDetailResponseDto.getMeetingInfo().getViewCount());
//    }
//    @Test
//    @DisplayName("????????????-???????????? ?????? ???")
//    void getMeeting1(){
//        //given
//
//        when(userRepository.findByKakaoId(userDetails.getUser().getKakaoId())).thenReturn(Optional.of(user1));
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.getMeeting(meeting.getId(),userDetails),"???????????? ?????? ??????????????????.");
//        //then
//        assertThat(exception.getMessage()).isEqualTo("???????????? ?????? ??????????????????.");
//    }
//
//    @Test
//    @DisplayName("????????????-???????????? null??? ???")
//    void getMeeting2(){
//        //given
//
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.getMeeting(meeting.getId(),userDetails));
//        //then
//        assertThat(exception.getMessage()).isEqualTo("???????????? ???????????????.");
//    }
//
//
//    @Test
//    @DisplayName("????????????")
//    void deleteMeeting(){
//        //given
//
//
//        when(meetingRepository.findById(meeting.getId())).thenReturn(Optional.of(meeting));
//
//        //when
//        meetingService.deleteMeeting(meeting.getId(),userDetails);
//
//
//        //then
//        verify(meetingRepository,times(1)).findById(meeting.getId());
//        verify(meetingRepository,times(1)).deleteById(meeting.getId());
//
//
//
//
//    }
//    @Test
//    @DisplayName("?????????_?????????_???????????????_??????_???")
//    void deletePost01()  {
//        //given
//         user2 = new User(
//                "?????????",
//                "asdf1234",
//                152L,
//                "haSLE2@naver.com",
//                UserRoleEnum.USER,
//                241L,
//                 "http://sljlet.com",
//                 location,
//                 "??????"
//        );
//        //user2 ?????? ??????
//        UserDetailsImpl userDetails2 = new UserDetailsImpl(user2);
//
//        when(meetingRepository.findById(meeting.getId())).thenReturn(Optional.of(meeting));
//
//        // when
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> meetingService.deleteMeeting(meeting.getId(), userDetails2), "?????? ????????? ????????????.");
//        //then
//
//        assertThat(exception.getMessage()).isEqualTo("?????? ????????? ????????????.");
//    }
//    @Test
//    @DisplayName("???????????? ???????????????")
//    void getMeetingLsit() {
//        //given
//        List<Meeting> meetingList=new ArrayList<>();
//        meetingList.add(meeting);
//
//        when(meetingRepository.findAllByOrderByModifiedAtDesc()).thenReturn(meetingList);
//
//        //when
//        List<MeetingTotalListResponseDto> meetingTotalListResponseDtoList = meetingService.getMeetingLsit();
//
//        //then
//        assertThat(meetingTotalListResponseDtoList.size()).isEqualTo(1);
//
//    }
//
//}