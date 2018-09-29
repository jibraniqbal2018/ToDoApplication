export class Task{
    constructor();
    constructor(
        public id ?: number,
        public taskName ?: string,
        public status : string= "Pending",
        public description?: string 
    ){};
}


