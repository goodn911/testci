//package com.example.foodmap.service;
//
//import com.example.foodmap.dto.meeting.MeetingCommentCreateRequestDto;
//import com.example.foodmap.dto.meeting.MeetingCommentResponseDto;
//import com.example.foodmap.dto.meeting.MeetingCreatRequestDto;
//import com.example.foodmap.dto.meeting.MeetingUpdateRequestDto;
//import com.example.foodmap.model.*;
//import com.example.foodmap.repository.MeetingCommentRepository;
//import com.example.foodmap.repository.MeetingRepository;
//import com.example.foodmap.repository.UserRepository;
//import com.example.foodmap.security.UserDetailsImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class MeetingCommentServiceTest {
//
//    @Autowired
//    MeetingRepository meetingRepository;
//    @Autowired
//    MeetingCommentRepository meetingCommentRepository;
//    @Autowired
//    MeetingService meetingService;
//    @Autowired
//    MeetingCommentService meetingCommentService;
//    @Autowired
//    UserRepository userRepository;
//
//    private Meeting meeting1;
//    private User user1;
//    private User user2;
//    private MeetingCreatRequestDto meetingCreatRequestDto;
//    private UserDetailsImpl userDetails1;
//    private UserDetailsImpl userDetails2;
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
//        location = new Location("?????????",123.231,12.234);
//        user1 = new User(
//                "?????????",
//                "asdf1234",
//                222L,
//                "hanghae99@naver.com",
//                UserRoleEnum.USER,
//                1L,
//                "http://sljlet.com",
//                location,
//                "??????"
//        );
//        user2 = new User(
//                "?????????",
//                "asdf1234",
//                152L,
//                "haSLE2@naver.com",
//                UserRoleEnum.USER,
//                241L,
//                "http://sljlet.com",
//                location,
//                "??????"
//        );
//
//
//        meetingTitle="??????????????? ??????????";
//        startDate = LocalDateTime.of(2021,12,24,05,00);
//        endDate = LocalDateTime.of(2021,12,25,12,00);
//        meetingDate = LocalDateTime.of(2021,12,28,14,00);
//        restaurant="???????????????";
//        limitPeople=3;
//        nowPeople=1;
//        content= "?????????";
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        userDetails1 = new UserDetailsImpl(user1);
//        userDetails2 = new UserDetailsImpl(user2);
//
//        meetingCreatRequestDto = new MeetingCreatRequestDto(
//                meetingTitle,startDate,restaurant,endDate,meetingDate,location,limitPeople,nowPeople,content
//        );
//
//
//        meeting1 = new Meeting(user1,meetingCreatRequestDto);
//        //????????????
//        meetingRepository.save(meeting1);
//
//
//    }
//
//    @Test
//    @DisplayName("??? ?????? ??????")
//    void ????????????(){
//        //given
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",null);
//
//        //when
//        meetingCommentService.createComment(meetingCommentCreateRequestDto,meeting1.getId(),userDetails1);
//
//        //then
//
//
//    }
//
//    @Test
//    @DisplayName("???????????? ??? ?????? ?????? ?????? ?????? -??????")
//    void ????????????0(){
//        //given
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",1L);
//
//        //when
//       ;
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            meetingCommentService.createComment(meetingCommentCreateRequestDto,meeting1.getId(),userDetails1);
//        });
//        //then
//        assertThat(exception.getMessage()).isEqualTo(null);
//    }
//    @Test
//    @DisplayName("?????? content null-??????")
//    void ????????????1() {
//        //given
//         content= null;
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto(content,null);
//
//        //when
//        Exception exception = assertThrows(NullPointerException.class, () -> {
//            new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null );
//        });
//
//        //then
//        assertThat(exception.getMessage()).isEqualTo("??????????????? ?????? ?????? ??? ?????????.");
//    }
//    @Test
//    @DisplayName("????????? null- ??????")
//    void ????????????2() {
//        //given
//        meeting1= null;
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto(content,1L);
//        //when
//        Exception exception = assertThrows(NullPointerException.class, () -> {
//            new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null);
//        });
//
//        //then
//        assertThat(exception.getMessage()).isEqualTo("???????????? ?????? ??????????????????.");
//    }
//
//
//
//
//    @Test
//    @DisplayName("?????? ??????")
//    void ????????????() {
//       //given
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",null);
//
//        MeetingComment meetingComment = new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null);
//        meetingCommentRepository.save(meetingComment);
//
//        MeetingUpdateRequestDto updateCommentDto =
//                new MeetingUpdateRequestDto("?????????????");
//        //when
//        meetingCommentService.updateComment(updateCommentDto,meetingComment.getId(),userDetails1);
//
//        //then
//        assertThat(meetingComment.getContent()).isEqualTo("?????????????");
//    }
//
//    @Test
//    @DisplayName("?????? ??????-????????????")
//    void ????????????1() {
//        //given
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",null);
//
//        MeetingComment meetingComment = new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null);
//        meetingCommentRepository.save(meetingComment);
//
//        MeetingUpdateRequestDto updateCommentDto =
//                new MeetingUpdateRequestDto("?????????????");
//        //when
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            meetingCommentService.updateComment(updateCommentDto,meetingComment.getId(),userDetails2);
//        });
//
//        assertThat(exception.getMessage()).isEqualTo("?????? ????????? ?????? ??? ??? ????????????.");
//    }
//    @Test
//    @DisplayName("?????? ??????")
//    void ????????????() {
//        //given
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",null);
//
//        MeetingComment meetingComment = new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null);
//        meetingCommentRepository.save(meetingComment);
//
//        //when
//        meetingCommentService.deleteComment(meetingComment.getId(),userDetails1);
//
//        //then
//
//    }
//    @Test
//    @DisplayName("?????? ??????-?????? ??????")
//    void ????????????1() {
//        MeetingCommentCreateRequestDto meetingCommentCreateRequestDto =
//                new MeetingCommentCreateRequestDto("?????? ???????????????.",null);
//
//        MeetingComment meetingComment = new MeetingComment(meetingCommentCreateRequestDto.getContent(),user1,meeting1,null);
//        meetingCommentRepository.save(meetingComment);
//
//        //when
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            meetingCommentService.deleteComment(meetingComment.getId(),userDetails2);
//        });
//
//        assertThat(exception.getMessage()).isEqualTo("?????? ????????? ?????? ??? ???????????????.");
//    }
//
//    @Test
//    @DisplayName("????????????")
//    void ????????????() {
//        //given
//        /**
//         * 1
//         *  2
//         *   4
//         *    6
//         *   5
//         *  3
//         *   7
//         * 8 ??? ?????? ??????
//         */
//        MeetingComment comment1 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("1???", null), meeting1.getId(), userDetails1);
//        MeetingComment comment2 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("2???", comment1.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment3 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("3???", comment1.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment4 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("4???", comment2.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment5 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("5???", comment2.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment6 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("6???", comment3.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment7 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("7???", comment4.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment8 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("8???", null), meeting1.getId(), userDetails1);
//
//
//        //when
//        List<MeetingCommentResponseDto> result = meetingService.commentAll(meeting1.getId(), userDetails1);
//
//        //then
//        assertThat(result.size()).isEqualTo(2); // ????????? ??????
//        assertThat(result.get(0).getChildren().size()).isEqualTo(2); // 1??? children
//        assertThat(result.get(0).getChildren().get(0).getChildren().size()).isEqualTo(2); // 2??? children
//        assertThat(result.get(0).getChildren().get(0).getChildren().get(0).getChildren().size()).isEqualTo(1); // 4??? children
//        assertThat(result.get(0).getChildren().get(0).getChildren().get(0)
//                .getChildren().get(0).getChildren().size()).isEqualTo(0); // 6??? children
//        assertThat(result.get(0).getChildren().get(0).getChildren().get(1).getChildren().size()).isEqualTo(0); // 5??? children
//        assertThat(result.get(0).getChildren().get(1).getChildren().size()).isEqualTo(1); // 1??? children
//        assertThat(result.get(0).getChildren().get(1).getChildren().get(0).getChildren().size()).isEqualTo(0); // 7??? children
//        assertThat(result.get(1).getChildren().size()).isEqualTo(0); // 8??? children
//    }
//    @Test
//    @DisplayName("???????????? ??????")
//    void ????????????1() {
//        //given
//        /**
//         * 1
//         *  2 2?????? ??????
//         *   4
//         *  3
//         */
//        MeetingComment comment1 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("1???", null), meeting1.getId(), userDetails1);
//        MeetingComment comment2 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("2???", comment1.getId()), meeting1.getId(), userDetails2);
//        MeetingComment comment3 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("3???", comment1.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment4 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("4???", comment2.getId()), meeting1.getId(), userDetails1);
//
//        MeetingUpdateRequestDto meetingUpdateRequestDto = new MeetingUpdateRequestDto("?????????2???");
//
//        //when
//        meetingCommentService.updateComment(meetingUpdateRequestDto,comment2.getId(),userDetails2);
//
//        //then
//        assertThat(comment2.getContent()).isEqualTo("?????????2???");
//        assertThat(comment1.getContent()).isEqualTo("1???");
//        assertThat(comment3.getContent()).isEqualTo("3???");
//        assertThat(comment4.getContent()).isEqualTo("4???");
//
//
//    }
//    @Test
//    @DisplayName("???????????? ????????? ???????????? ?????? ??????")
//    void ????????????2() {
//        //given
//        /**
//         * 1
//         *  2 2??? ??????
//         *   4 ?????????????????? ??????
//         *  3
//         */
//        MeetingComment comment1 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("1???", null), meeting1.getId(), userDetails1);
//        MeetingComment comment2 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("2???", comment1.getId()), meeting1.getId(), userDetails2);
//        MeetingComment comment3 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("3???", comment1.getId()), meeting1.getId(), userDetails1);
//        MeetingComment comment4 = meetingCommentService.createComment(new MeetingCommentCreateRequestDto("4???", comment2.getId()), meeting1.getId(), userDetails1);
//
//        //when
//        Long commentId = meetingCommentService.deleteComment(comment2.getId(), userDetails2);
//
//        //then
//        List<MeetingCommentResponseDto> result = meetingService.commentAll(meeting1.getId(), userDetails1);
//
//        assertThat(result.get(0).getChildren().size()).isEqualTo(1); //1??? children
//        assertThat(result.get(0).getChildren().get(0).getChildren().size()).isEqualTo(0); // 2??? children
//        assertThat(result.get(0).getChildren().get(0).getContent()).isEqualTo("3???");
//
//
//    }
//
//
//
//
//}