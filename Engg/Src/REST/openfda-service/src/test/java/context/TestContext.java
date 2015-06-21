package context;

import org.jderive.repository.AgeGroupRepository;
import org.jderive.repository.WeightGroupRepository;
import org.jderive.repository.impl.AgeGroupRepositoryImpl;
import org.jderive.repository.impl.WeightGroupRepositoryImpl;
import org.jderive.service.AgeGroupService;
import org.jderive.service.WeightGroupService;
import org.jderive.service.impl.AgeGroupServiceImpl;
import org.jderive.service.impl.WeightGroupServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

	@Bean
	public WeightGroupService weightGroupService() {
		return Mockito.mock(WeightGroupServiceImpl.class);
	}
	
	@Bean
	public WeightGroupRepository weightGroupRepository() {
		return Mockito.mock(WeightGroupRepositoryImpl.class);
	}
	
	@Bean
	public AgeGroupService ageGroupService() {
		return Mockito.mock(AgeGroupServiceImpl.class);
	}
	
	@Bean
	public AgeGroupRepository ageGroupRepository() {
		return Mockito.mock(AgeGroupRepositoryImpl.class);
	}
}
