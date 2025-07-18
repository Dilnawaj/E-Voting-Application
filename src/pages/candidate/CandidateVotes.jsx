import React, { use, useContext, useEffect, useState } from "react";
import { CandidateContext } from "../../context/CandidateContext";
import { getLiveVotes } from "../../api/candidateApi";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  PieChart,
  Pie,
  Cell,
  Legend,
} from "recharts";
import { Container,Button } from "reactstrap";
import { Link } from "react-router-dom";
import Base from "../../Base";
function CandidateVotes() {
  const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

  const { candidate } = useContext(CandidateContext);

  const [liveVotes, setLiveVotes] = useState([]);
  const fetchLiveVotes = async () => {
    try {
      const response = await getLiveVotes(candidate.constituency);
      console.log("Live Votes Data:", response);
      setLiveVotes(response);
    } catch (error) {
      console.error("Error fetching candidate data:", error);
    }
  };
  const transformedVotes = liveVotes.map((vote) => ({
    ...vote,
    displayName: `${vote.candidateName} (${vote.party})`,
  }));

  useEffect(() => {
    fetchLiveVotes();

    const interval = setInterval(fetchLiveVotes, 5000); // Fetch every 5 seconds
    return () => clearInterval(interval); // Cleanup on unmount
  }, []);

  const totalVotes = liveVotes.reduce((acc, c) => acc + c.totalVotes, 0);

  return (
    <Base>
    <div className="live-votes-container">
      <h2>🔴 Live Vote Analytics</h2>
      <h3>Total Votes Cast: {totalVotes}</h3>

      <div className="charts-container">
        <div className="bar-chart">
          <BarChart width={500} height={300} data={liveVotes}>
            <XAxis dataKey="candidateName" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="totalVotes" fill="#8884d8" />
          </BarChart>
        </div>

        <div className="pie-chart">
          <PieChart width={300} height={300}>
            <Pie
              data={transformedVotes}
              dataKey="totalVotes"
              nameKey="displayName"
              outerRadius={100}
              label
            >
              {transformedVotes.map((entry, index) => (
                <Cell
                  key={`cell-${index}`}
                  fill={COLORS[index % COLORS.length]}
                />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </div>
      </div>
       <Container className="text-center" style={{ marginTop: "20px" }}>
              <div className="d-flex justify-content-center">
              
                <Link to="/candidate/dashboard">
                  <Button color="info" size="lg" className="mr-2 mx-2">
                    Back
                  </Button>
                </Link>
              </div>
            </Container>
    </div>
    </Base>
  );
}

export default CandidateVotes;
