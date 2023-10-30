package com.uit.gatewayserver.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
public class KeyCloakRoleConverter implements Converter<Jwt,Collection<GrantedAuthority>>  {

    @Override
    @Nullable
    public Collection<GrantedAuthority> convert(Jwt source) {
        // TODO Auto-generated method stub
        Map<String,Object> realmAccess =(Map<String,Object>) source.getClaims().get("realm_access");
        if(realmAccess==null|| realmAccess.isEmpty()){
            return new ArrayList<>();
         
        }
        Collection<GrantedAuthority> returnValue=((List<String>) realmAccess.get("roles")).stream().map(roleName->"ROLE_"+roleName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
        return returnValue;
    
    }
       
    }

    

