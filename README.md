## Blockchain

Deque of blocks that contain transactions, which in turn contain payloads.

## Block

Timestamp: When this block was created

Transactions: The list of transactions contained in this block

Proof: The proof of work for this block

Previous hash: The hash of the block that came before

## Transaction

Sender: Creator of the transaction, optional.

Recipient: Recipient of the payload, optional.

Payload: The payload, which may be encrypted.

## Payload

The payload is a JSON object, because JSON has no schema this leaves the payload open to any use, for binary data it can be included as BSON which is a standard that describes the storage of binary data in JSON.

If the payload is intended for a single person it is encrypted using that persons public key which should be available in the block chain along with their address unencrypted.

## Proof of work

Block funnel creates a hash of the block that includes the timestamp, previous hash and a proof of work value which is a 64 bit number, the hash must end in 00 this may take some time to generate but can be easily verified

As it uses the previous hash when the block is verified it means it is in the correct position, with consensus this gives the chain immutability and security.
