package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WeatherReport.class, WeatherReportController.class, WeatherReportService.class} )
class WeatherApplicationTests {

	@Test
	void contextLoads() {
	}


	@Mock
	private WeatherReportService weatherReportService;

	@InjectMocks
	private WeatherReportController weatherReportController;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	// este test prueba ambas funcionalidades en un solo método
	@Test
	public void testGetWeatherReport() {
		double latitude = 37.8267;
		double longitude = -122.4233;
		WeatherReport mockReport = new WeatherReport();
		mockReport.setTemperature(20.0);
		mockReport.setHumidity(80.0);

		when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockReport);

		weatherReportController = new WeatherReportController(weatherReportService);
		WeatherReport report = weatherReportController.getWeatherReport(latitude, longitude);

		assertEquals(mockReport.getTemperature(), report.getTemperature());
		assertEquals(mockReport.getHumidity(), report.getHumidity());
	}

}
