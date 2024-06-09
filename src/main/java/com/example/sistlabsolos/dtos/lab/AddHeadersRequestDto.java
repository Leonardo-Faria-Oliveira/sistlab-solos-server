package com.example.sistlabsolos.dtos.lab;

import org.springframework.web.multipart.MultipartFile;



public record AddHeadersRequestDto (

   
    MultipartFile  header1,

  
    MultipartFile  header2,


    MultipartFile  header3  

) {
    
}
