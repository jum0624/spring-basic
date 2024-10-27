package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void pureContainer() throws Exception {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적요한 객체 사용")
    void singletonServiceTest() {
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();

        assertThat(singleTonService1).isSameAs(singleTonService2);

        // same vs equal
        // same (==) : 인스턴스 비교
        // equal(equals) : 객체 값 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
