package com.durian.codes.RestKotlinProject.controller

import com.durian.codes.RestKotlinProject.service.StudentService
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest(controllers = [StudentController::class])
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
internal class StudentControllerIT {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var studentService: StudentService

    @Test
    fun shouldGetBrandList() {
        given(brandService.listBrands()).willReturn(Arrays.asList(validBrandDto(), validBrandDto()))
        mockMvc.perform(
            get("/students")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$.[0].name", `is`("Valid Brand")))
            .andExpect(jsonPath("$.[1].name", `is`("Valid Brand")))
    }

    @Test
    fun shouldGetBrandById() {
        val brandDto: BrandDto = validBrandDto()
        brandDto.setId(1L)
        brandDto.setCreatedBy(1L)
        brandDto.setUpdatedBy(2L)
        given(brandService.getBrand(brandDto.getId())).willReturn(brandDto)
        mockMvc.perform(
            get("/brands/{id}", brandDto.getId())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", `is`(1)))
            .andExpect(jsonPath("$.name", `is`("Valid Brand")))
            .andExpect(jsonPath("$.createdBy", `is`(1)))
            .andExpect(jsonPath("$.updatedBy", `is`(2)))
    }

    @Test
    fun shouldThrowItemNotFoundExceptionWhenBrandNotExistToGet() {
        given(brandService.getBrand(anyLong())).willThrow(ItemNotFoundException("Brand not found for given ID"))
        mockMvc.perform(
            get("/brands/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", `is`("Brand not found for given ID")))
    }
}
