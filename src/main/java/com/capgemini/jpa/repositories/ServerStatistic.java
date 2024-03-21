package com.capgemini.jpa.repositories;

import com.capgemini.jpa.entities.Server;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ServerStatistic {
    Server server;
    long count;

}
