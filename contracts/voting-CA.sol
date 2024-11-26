// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

// Define the contract for Voting
contract Voting {
    
    // Structure to store candidate details
    struct Candidate {
        uint id;            // Unique ID for each candidate
        string name;        // Name of the candidate
        uint voteCount;     // Number of votes the candidate has received
    }

    // Mapping to store candidates by their ID
    mapping(uint => Candidate) public candidates;

    // Mapping to store the voting status of each address (whether the address has voted)
    mapping(address => bool) public voters;

    // Variable to track the total number of candidates
    uint public candidatesCount;

    // Variable to track the total number of votes cast in the election
    uint public totalVotes;
}
