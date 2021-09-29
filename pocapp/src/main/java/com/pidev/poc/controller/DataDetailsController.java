package com.pidev.poc.controller;

import com.pidev.poc.dto.DataDetailsDto;
import com.pidev.poc.jaxb.DataDetailsType;
import com.pidev.poc.utils.JaxbUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Controller
@RequestMapping("/data")
public class DataDetailsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JaxbUtils jaxbUtils;

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable(name = "msg") String pMsg) {
        log.info("echo called");
        return pMsg;
    }

    @GetMapping
    public String getDataDetails(Model model) throws IOException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("xml/dataDetailsExample.xml").getPath());
        DataDetailsType dataDetailsType = jaxbUtils.unmarshal(DataDetailsType.class,  new FileInputStream(file));

        DataDetailsDto dataDetailsDto = convertToDto(dataDetailsType);
        model.addAttribute("details", dataDetailsDto);
        return "details";
    }

    @GetMapping("/input")
    public String detailsForm(Model model) {
        model.addAttribute("details", new DataDetailsDto());
        return "input";
    }

    @PostMapping("/input")
    public String detailsSubmit(@ModelAttribute DataDetailsDto details, Model model) throws ParseException {
        model.addAttribute("details", details);

        DataDetailsType dataDetailsType = convertToEntity(details);
        jaxbUtils.jaxbObjectToXML(dataDetailsType);

        return "details";
    }

    private DataDetailsDto convertToDto(DataDetailsType dataDetailsType) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        DataDetailsDto dataDetailsDto = modelMapper.map(dataDetailsType, DataDetailsDto.class);
//        postDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return dataDetailsDto;
    }

    private DataDetailsType convertToEntity(DataDetailsDto dataDetailsDto) throws ParseException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        DataDetailsType dataDetailsType = modelMapper.map(dataDetailsDto, DataDetailsType.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));

//        if (dataDetailsDto.getId() != null) {
//            DataDetailsType oldPost = postService.getPostById(dataDetailsDto.getId());
//            dataDetailsType.setRedditID(oldPost.getRedditID());
//            dataDetailsType.setSent(oldPost.isSent());
//        }
        return dataDetailsType;
    }
}
