import React, { Component } from "react";
import styled, { keyframes } from "styled-components";
// Source: https://www.pluralsight.com/guides/how-to-pass-data-between-react-components

const BounceAnimation = keyframes`
  0% { margin-bottom: 0; }
  50% { margin-bottom: 15px }
  100% { margin-bottom: 0 }
`;

const DotWrapper = styled.div`
  display: flex;
  align-items: flex-end;
`;

const Dot0s = styled.div`
  background-color: black;
  border-radius: 50%;
  width: 10px;
  height: 10px;
  margin: 0 5px;
  
  /* Animation */
  animation: ${BounceAnimation} 0.5s linear infinite;
  animation-delay: 0s;
`;

const Dot01s = styled.div`
  background-color: black;
  border-radius: 50%;
  width: 10px;
  height: 10px;
  margin: 0 5px;
  
  /* Animation */
  animation: ${BounceAnimation} 0.5s linear infinite;
  animation-delay: 0.1s;
`;

const Dot02s = styled.div`
  background-color: black;
  border-radius: 50%;
  width: 10px;
  height: 10px;
  margin: 0 5px;
  
  /* Animation */
  animation: ${BounceAnimation} 0.5s linear infinite;
  animation-delay: 0.2s;
`;

class LoadingDots extends Component {
    render() {
        return (
            <DotWrapper>
                <Dot0s />
                <Dot01s />
                <Dot02s />
            </DotWrapper>
        )
    }
}
export default LoadingDots