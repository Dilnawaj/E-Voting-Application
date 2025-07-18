import React, { useContext, useEffect, useState } from "react";
import { getVoterInsights } from "../../api/voterApi";
import { CandidateContext } from "../../context/CandidateContext";
import { Container,Button } from "reactstrap";
import { Link } from "react-router-dom";
import {
  PieChart,
  Pie,
  Tooltip,
  Cell,
  ResponsiveContainer,
} from "recharts";
import Base from "../../Base";

const COLORS = ["#4CAF50", "#FF9800", "#03A9F4", "#E91E63", "#FFC107", "#9C27B0"];

function VoterInsightsPage() {
  const [insights, setInsights] = useState(null);
  const { candidate } = useContext(CandidateContext);
const fetchInsights = async () => {
      try {
        const response = await getVoterInsights(candidate.constituency);
        setInsights(response);
      } catch (error) {
        console.error("Error fetching voter insights:", error);
      }
    };
  useEffect(() => {
    
    fetchInsights();
  }, []);

  const getPieData = (labels) =>
    labels.map((label) => ({
      name: label.label,
      value: label.value,
    }));

  return (
    <Base >
    <div className="voter-insights-container">
      <h2>Voter Insights - {candidate.constituency}</h2>

      {insights ? (
        <>
          <div className="voter-summary">
            <p>Total Voters: {insights.totalVoters}</p>
            <p>Average Age: {insights.averageAge}</p>
            <p>Male Voters: {insights.totalMale} ({insights.malePercentage}%)</p>
            <p>Female Voters: {insights.totalFemales} ({insights.femalePercentage}%)</p>
            <p>Hindu Voters: {insights.totalHindu} ({insights.hinduPercentage}%)</p>
            <p>Muslim Voters: {insights.totalMuslim} ({insights.muslimPercentage}%)</p>
            <p>Young Voters: {insights.totalYoung} ({insights.youngPercentage}%)</p>
            <p>Senior Voters: {insights.totalSenior} ({insights.seniorPercentage}%)</p>
          </div>

          <div className="charts-section">
            <div className="chart-box">
              <h4 style={{ textAlign: "center" }}>Gender Distribution</h4>
              <ResponsiveContainer width={300} height={300}>
                <PieChart>
                  <Pie
                    data={getPieData([
                      { label: "Male", value: insights.totalMale },
                      { label: "Female", value: insights.totalFemales },
                    ])}
                    dataKey="value"
                    nameKey="name"
                    cx="50%"
                    cy="50%"
                    outerRadius={100}
                    label
                  >
                    <Cell fill="#03A9F4" />
                    <Cell fill="#E91E63" />
                  </Pie>
                  <Tooltip />
                </PieChart>
              </ResponsiveContainer>
            </div>

            <div className="chart-box">
              <h4 style={{ textAlign: "center" }}>Religion Distribution</h4>
              <ResponsiveContainer width={300} height={300}>
                <PieChart>
                  <Pie
                    data={getPieData([
                      { label: "Hindu", value: insights.totalHindu },
                      { label: "Muslim", value: insights.totalMuslim },
                    ])}
                    dataKey="value"
                    nameKey="name"
                    cx="50%"
                    cy="50%"
                    outerRadius={100}
                    label
                  >
                    <Cell fill="#4CAF50" />
                    <Cell fill="#FF9800" />
                  </Pie>
                  <Tooltip />
                </PieChart>
              </ResponsiveContainer>
            </div>

            <div className="chart-box">
              <h4 style={{ textAlign: "center" }}>Age Group Distribution</h4>
              <ResponsiveContainer width={300} height={300}>
                <PieChart>
                  <Pie
                    data={getPieData([
                      { label: "Young", value: insights.totalYoung },
                      { label: "Senior", value: insights.totalSenior },
                    ])}
                    dataKey="value"
                    nameKey="name"
                    cx="50%"
                    cy="50%"
                    outerRadius={100}
                    label
                  >
                    <Cell fill="#FFC107" />
                    <Cell fill="#9C27B0" />
                  </Pie>
                  <Tooltip />
                </PieChart>
              </ResponsiveContainer>
            </div>
          </div>
        </>
      ) : (
        <div style={{ textAlign: "center" }}>
          <h3>Loading insights...</h3>
        </div>
      )}
      
       <Container className="text-center"  style={{ marginTop: "20px" }}>
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

export default VoterInsightsPage;
