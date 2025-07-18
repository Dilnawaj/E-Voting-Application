package org.example.model;

public class VoteStats {
    private String candidateName;
    private String party;
    private long totalVotes;

    public VoteStats(String candidateName, String party, long totalVotes) {
        this.candidateName = candidateName;
        this.party = party;
        this.totalVotes = totalVotes;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }
}
