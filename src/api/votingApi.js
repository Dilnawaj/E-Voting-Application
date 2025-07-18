import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"vote-casting";

export const doVote = async (vote)=>{
  const response = await axios.post(BASE_URL, vote);
  return response.data;
}

export const castVote = async (candidateId, aadharNumber) => {
  console.log("Casting vote for candidateId:", candidateId, "with aadharNumber:", aadharNumber);
  const response = await axios.get(
    `${BASE_URL}/cast?candidateId=${candidateId}&aadharNumber=${aadharNumber}`
  );
  return response.data;
};
