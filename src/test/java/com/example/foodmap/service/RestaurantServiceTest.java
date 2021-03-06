//package com.example.foodmap.service;
//
//import com.example.foodmap.dto.Restaurant.RestaurantDetailResponseDto;
//import com.example.foodmap.dto.Restaurant.RestaurantLikesDto;
//import com.example.foodmap.dto.Restaurant.RestaurantResponseDto;
//import com.example.foodmap.dto.Restaurant.RestaurantSaveRequestDto;
//import com.example.foodmap.model.*;
//import com.example.foodmap.repository.RestaurantLikesRepository;
//import com.example.foodmap.repository.RestaurantRepository;
//import com.example.foodmap.repository.UserRepository;
//import com.example.foodmap.security.UserDetailsImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.awt.print.Pageable;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//
//@ExtendWith(MockitoExtension.class)
//class RestaurantServiceTest {
//
//    @InjectMocks
//    RestaurantService restaurantService;
//
//    @Mock
//    RestaurantRepository restaurantRepository;
//
//    @Mock
//    StorageService storageService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    RestaurantLikesRepository restaurantLikesRepository;
//
//    private UserDetailsImpl userDetailsNull;
//    private UserDetailsImpl userDetails01;
//    private RestaurantSaveRequestDto saveRequestDto;
//    private Restaurant restraurant01;
//    private Restaurant restraurant02;
//
//    @BeforeEach
//    public void setup() {
//
//        //?????? 01 ??????
//        Location userLocation = Location.builder()
//                .address("?????? ????????? ????????? 100")
//                .latitude(127.1163593869371)
//                .longitude(127.1163593869371)
//                .build();
//
//        User user1 = User.builder()
//                .id(1L)
//                .username("?????????")
//                .kakaoId(1L)
//                .email("test@naver.com")
//                .level(1L)
//                .password("password")
//                .role(UserRoleEnum.USER)
//                .build();
//
//        userDetails01 = new UserDetailsImpl(user1);
//        //??????
//
//        restraurant01 = Restaurant.builder()
//               .id(1L)
//               .user(userDetails01.getUser())
//                .restaurantName("???????????????")
//                .latitude(35.123)
//                .longitude(27.152)
//                .address("????????? ????????? ????????? 100")
//                .restaurantType("??????")
//                .fried(true)
//                .sundae("?????????")
//                .image("default.png")
//                .build();
//
//
//        restraurant02 = Restaurant.builder()
//                .id(2L)
//                .user(userDetails01.getUser())
//                .restaurantName("???????????????")
//                .latitude(15.1673)
//                .longitude(27.1234)
//                .address("????????? ????????? 14")
//                .restaurantType("??????")
//                .fried(false)
//                .sundae("????????????")
//                .image("dfdfwerd.jpg")
//                .build();
//    }
//
////    @Test
////    @DisplayName("?????????-?????? ??????")
////    void saveRestaurant() {
////        //given
////        saveRequestDto = RestaurantSaveRequestDto.builder()
////                .restaurantName("???????????????")
////                .latitude(37.49054133559972)
////                .longitude(126.94830822613552)
////                .address("??????????????? ????????? ????????? 38")
////                .restaurantType("??????")
////                .fried(true)
////                .sundae("?????????")
////                .tteokbokkiType("??????")
////                .image(null)
////                .build();
////        when(userRepository.findById(userDetails01.getUser().getId())).thenReturn(Optional.of(userDetails01.getUser()));
////        Restaurant newRestaurant = new Restaurant(saveRequestDto, userDetails01.getUser());
////
////        //when
////        restaurantService.saveRestaurant(saveRequestDto, userDetails01.getUser());
////        //then
////        //return ?????? void??? ????????? ????????????
////
////        doNothing().when(restaurantService).saveRestaurant(isA(RestaurantSaveRequestDto.class), isA(User.class));
////        restaurantService.saveRestaurant(saveRequestDto, userDetails01.getUser());
////
////        verify(restaurantService, times(1)).saveRestaurant(saveRequestDto, userDetails01.getUser());
////    }
//
//    @Test
//    @DisplayName("????????????")
//    void getList() {
//        //given
//        List<Restaurant> restaurants = new ArrayList<>();
//        restaurants.add(restraurant01);
//        restaurants.add(restraurant02);
//
//        PageRequest pageable = PageRequest.of(0, 2);
//        Page<Restaurant> pagedRestaurant = new PageImpl<>(restaurants);
//        given(restaurantRepository.findAll(pageable)).willReturn(pagedRestaurant);
//        //when
//        List<RestaurantResponseDto> result = restaurantService.getRestaurants(userDetails01.getUser(), 0, 2);
//
//        //then
//        for (RestaurantResponseDto r : result) {
//            System.out.println(r.getRestaurantName());
//        }
//
//        assertThat(result.size()).isEqualTo(2);
//        assertThat(result.get(0).getRestaurantName()).isEqualTo("???????????????");
//        verify(restaurantRepository, times(1)).findAll(pageable);
//
//    }
//
//
//    @Test
//    @DisplayName("?????? ?????? ??????")
//    void getDetailInfo() {
//        //given
//        Long restraurant02Id = restraurant02.getId();
//
//        when(restaurantRepository.findById(restraurant02Id)).thenReturn(Optional.of(restraurant02));
//        //when
//        RestaurantDetailResponseDto detailInfo = restaurantService.getRestaurantDetail(restraurant02Id, userDetails01.getUser());
//
//        //then
//        assertThat(detailInfo.getRestaurantId()).isEqualTo(restraurant02.getId());
//        assertThat(detailInfo.getRestaurantName()).isEqualTo(restraurant02.getRestaurantName());
//        assertThat(detailInfo.getAddress()).isEqualTo(restraurant02.getAddress());
//        assertThat(detailInfo.getLatitude()).isEqualTo(restraurant02.getLatitude());
//        assertThat(detailInfo.getLongitude()).isEqualTo(restraurant02.getLongitude());
//        assertThat(detailInfo.getRestaurantType()).isEqualTo(restraurant02.getRestaurantType());
//        assertThat(detailInfo.getFried()).isEqualTo(restraurant02.getFried());
//        assertThat(detailInfo.getSundae()).isEqualTo(restraurant02.getSundae());
//
//
//    }
//}