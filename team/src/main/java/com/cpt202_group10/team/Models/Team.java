package com.cpt202_group10.team.Models;

public class Team
{
    private String name;
    private int memberCount;
    

    public Team() {
        // Default constructor required for calls to DataSnapshot.getValue(Team.class)
    }

    public Team(String name, int memberCount) {
        this.name = name;
        this.memberCount = memberCount;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

}
