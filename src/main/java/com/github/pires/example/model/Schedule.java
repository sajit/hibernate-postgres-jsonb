/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.example.model;

import com.github.pires.example.hibernate.user.types.JSONBUserType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by sajit on 11/28/15.
 */
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class, parameters = {
        @Parameter(name = JSONBUserType.CLASS,
                value = "com.github.pires.example.model.Schedule")})
@Entity
@Table
public class Schedule {

    @Id
    private Integer id;

    @Column
    private String name;
    @Type(type = "jsonb")
    private List<List<Slot>> slots;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<List<Slot>> getSlots() {
        return slots;
    }

    public void setSlots(List<List<Slot>> slots) {
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
