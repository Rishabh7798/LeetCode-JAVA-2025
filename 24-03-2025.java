3169. Count Days Without Meetings

class Solution {
    public int countDays(int days, int[][] meetings) {
        int freeDays = days;
        TreeMap<Integer, Integer> meetingDays = new TreeMap<>();
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int overlapDays = 0;

            Map.Entry<Integer, Integer> previousMeetingDays = meetingDays.floorEntry(start);
            if (previousMeetingDays != null && previousMeetingDays.getValue() >= start - 1) {
                if (previousMeetingDays.getValue() >= end) continue; // Complete overlap
                overlapDays = previousMeetingDays.getValue() - previousMeetingDays.getKey() + 1;
                start = previousMeetingDays.getKey();
            }

            Map.Entry<Integer, Integer> nextMeetingDays = meetingDays.ceilingEntry(start + 1);
            while (nextMeetingDays != null && nextMeetingDays.getKey() <= end + 1) {
                meetingDays.remove(nextMeetingDays.getKey());
                overlapDays += nextMeetingDays.getValue() - nextMeetingDays.getKey() + 1;
                if (nextMeetingDays.getValue() >= end) {
                    end = nextMeetingDays.getValue();
                    break;
                }
                nextMeetingDays = meetingDays.ceilingEntry(start + 1);
            }

            meetingDays.put(start, end);
            freeDays -= (end - start + 1) - overlapDays;
            if (freeDays == 0) break;
        }
        return freeDays;

    }
}
