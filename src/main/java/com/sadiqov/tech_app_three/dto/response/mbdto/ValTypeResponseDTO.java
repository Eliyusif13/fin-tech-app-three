package com.sadiqov.tech_app_three.dto.response.mbdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "ValType")
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ValTypeResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlAttribute(name = "Type")
    String type;
    @XmlElement(name = "Valute")
    List<ValuteResponseDTO> valuteList;


}
