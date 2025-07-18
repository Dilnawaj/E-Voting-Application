import Footer from "./components/Footer";
import Header from "./components/Header";

const Base = ({children}) => {
  return (
    <div className="container-fluid d-flex flex-column" style={{ minHeight: "100vh" }}>
      <Header  />
      <main style={{ flex: 1 }}>{children}</main>
      <Footer />
    </div>
  );
};

export default Base;
