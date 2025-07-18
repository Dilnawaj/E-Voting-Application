import React from "react";

const Footer = () => {
  return (
    <footer style={styles.footer}>
      <p style={styles.text}>© {new Date().getFullYear()} E-Voting System. All rights reserved.</p>
    </footer>
  );
};

const styles = {
  footer: {
    background: "#1a1a1a",
    color: "#ccc",
    textAlign: "center",
    padding: "20px 10px",
    marginTop: "auto",
  },
  text: {
    fontSize: "14px",
  },
};

export default Footer;
