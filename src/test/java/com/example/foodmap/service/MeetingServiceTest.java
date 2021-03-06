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
//       location = new Location("강남구",123.231,12.234);
//       user1 = new User(
//                "파이리",
//                "asdf1234",
//                222L,
//                "hanghae99@naver.com",
//                UserRoleEnum.USER,
//                1L,
//               "http://sljlet.com",
//               location,
//               "지우"
//        );
//
//
//        meetingTitle="악어떡볶기 가실분?";
//        startDate = LocalDateTime.of(2021,12,24,05,00);
//        endDate = LocalDateTime.of(2021,12,25,12,00);
//        meetingDate = LocalDateTime.of(2021,12,28,14,00);
//        restaurant="악어떡볶이";
//        limitPeople=5;
//        nowPeople=1;
//        content= "졸맛집";
//
//
//        meetingCreatRequestDto = new MeetingCreatRequestDto(
//                meetingTitle,startDate,restaurant,endDate,meetingDate,location,limitPeople,nowPeople,content
//        );
//
//        //user1 모임 등록
//        meeting = new Meeting(user1,meetingCreatRequestDto);
//        //user1 유저 정보
//        userDetails = new UserDetailsImpl(user1);
//
//    }
//    @Test
//    @DisplayName("모임등록")
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
//    @DisplayName("모임등록 - 사용자가 null일떄")
//    void create1(){
//        //given
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.creatMeeting(meetingCreatRequestDto,userDetails));
//        //then
//        assertThat(exception.getMessage()).isEqualTo("로그인이 필요합니다.");
//
//
//    }
//    @Test
//    @DisplayName("모임조회")
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
//    @DisplayName("모임조회-게시물이 없을 때")
//    void getMeeting1(){
//        //given
//
//        when(userRepository.findByKakaoId(userDetails.getUser().getKakaoId())).thenReturn(Optional.of(user1));
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.getMeeting(meeting.getId(),userDetails),"존재하지 않는 게시물입니다.");
//        //then
//        assertThat(exception.getMessage()).isEqualTo("존재하지 않는 게시물입니다.");
//    }
//
//    @Test
//    @DisplayName("모임조회-사용자가 null일 때")
//    void getMeeting2(){
//        //given
//
//
//        //when
//        NullPointerException exception = assertThrows(NullPointerException.class,
//                () -> meetingService.getMeeting(meeting.getId(),userDetails));
//        //then
//        assertThat(exception.getMessage()).isEqualTo("로그인이 필요합니다.");
//    }
//
//
//    @Test
//    @DisplayName("모임삭제")
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
//    @DisplayName("모집글_삭제시_삭제권한이_없을_때")
//    void deletePost01()  {
//        //given
//         user2 = new User(
//                "꼬부기",
//                "asdf1234",
//                152L,
//                "haSLE2@naver.com",
//                UserRoleEnum.USER,
//                241L,
//                 "http://sljlet.com",
//                 location,
//                 "지우"
//        );
//        //user2 유저 정보
//        UserDetailsImpl userDetails2 = new UserDetailsImpl(user2);
//
//        when(meetingRepository.findById(meeting.getId())).thenReturn(Optional.of(meeting));
//
//        // when
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> meetingService.deleteMeeting(meeting.getId(), userDetails2), "삭제 권한이 없습니다.");
//        //then
//
//        assertThat(exception.getMessage()).isEqualTo("삭제 권한이 없습니다.");
//    }
//    @Test
//    @DisplayName("모임전체 조회리스트")
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